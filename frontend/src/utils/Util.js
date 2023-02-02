export const getPhotoPath = (photoId) => `/photo-api/assets/${photoId}`;

export const getUserObjectFromLocalStorage = () => {
  const user = localStorage.getItem("user");
  if (user) {
    return JSON.parse(user);
  }

  return null;
};

export const setUserObjectFromLocalStorage = (user) => {
  localStorage.setItem("user", JSON.stringify(user));
};

async function toDataURL(url) {
  const blob = await fetch(url).then((res) => res.blob());
  return URL.createObjectURL(blob);
}

export async function downloadImage(url, imageFileName) {
  const a = document.createElement("a");
  a.href = await toDataURL(url);
  a.download = imageFileName + ".jpeg";
  document.body.appendChild(a);
  a.click();
  document.body.removeChild(a);
}
