const getPhotographerTableData = (jsonData) => {
  const data = jsonData.photos
    // .slice(0, 2)
    .reduce(
      (acc, { photographer, photographer_id, photographer_url, ...rest }) => {
        return {
          ...acc,
          [photographer_id]: {
            photographerName: photographer,
            photographerId: photographer_id,
            photographerProfile: photographer_url,
          },
        };
      },
      {}
    );
  console.log("Photographer Data", JSON.stringify(Object.values(data)));
};

const getPhotosTableData = (jsonData) => {
  const promisesData = jsonData.photos
    //.slice(0, 5)
    .map(async ({ id, src: { medium, large, large2x }, ...rest }) => {
      const fileM = await getResults(id, medium, "md");
      const fileL = await getResults(id, large, "lg");
      const fileO = await getResults(id, large2x, "o");

      return { data: rest, url: { fileM, fileL, fileO, id } };
    });

  Promise.all(promisesData).then((thenData) => {
    console.log(thenData);
    var zip = new JSZip();

    // var img = zip.folder("images");

    const urlData = thenData.map(
      ({
        data: { id, width, height, alt, photographer_id, avg_color },
        url: { fileM, fileL, fileO },
      }) => {
        zip.file(fileM.fileName, fileM.uri, { base64: true });
        zip.file(fileL.fileName, fileL.uri, { base64: true });
        zip.file(fileO.fileName, fileO.uri, { base64: true });

        return {
          photoId: id,
          // width,
          // height,
          title: alt,
          avgColor: avg_color,
          photographerId: photographer_id,
          imageMediumSize: fileM.fileName,
          imageLargeSize: fileL.fileName,
          imageOrignalSize: fileO.fileName,
        };
      }
    );
    zip.generateAsync({ type: "blob" }).then(function (content) {
      // see FileSaver.js
      saveAs(content, "photos.zip");
    });

    console.log("Photos Data", JSON.stringify(urlData));
  });
};

function getBase64(imageUrl) {
  console.log(imageUrl);
  return axios
    .get(imageUrl, { responseType: "blob" })
    .then(function (response) {
      const { data } = response;

      return new Promise((res, rej) => {
        const reader = new window.FileReader();
        reader.readAsDataURL(data);
        reader.onloadend = () => res(reader.result);
        reader.onerror = rej;
      });
    });
}

function dataURLtoFile(dataurl, filename) {
  const arr = dataurl.split(","),
    mime = arr[0].match(/:(.*?);/)[1],
    bstr = atob(arr[1]);

  let n = bstr.length;

  let u8arr = new Uint8Array(n);
  while (n--) {
    u8arr[n] = bstr.charCodeAt(n);
  }
  return new File([u8arr], filename, { type: mime });
}

const getResults = async (id, url, size) => {
  try {
    const dataUrl = await getBase64(url);

    const fileData = dataURLtoFile(dataUrl, `photo-${id}.jpeg`);

    return { uri: fileData, fileName: `photo-${id}-${size}.jpeg` };
  } catch (error) {}
};

document.getElementById("inputfile").addEventListener("change", function () {
  let fileReader = new FileReader();
  fileReader.onload = function () {
    let parsedJSON = JSON.parse(fileReader.result);
    console.log(parsedJSON);

    getPhotographerTableData(parsedJSON);
    getPhotosTableData(parsedJSON);
  };
  fileReader.readAsText(this.files[0]);
});

/*
 *
 */
