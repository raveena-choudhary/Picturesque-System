import React, { useEffect, useState } from "react";
import { GlobalContext } from "./../../Init";
import ImageCards from "./../../components/ImageCards/ImageCards";

const MyCollectionPage = () => {
  const {
    state: { photos, user, photoLikesMap },
    // getAllPhotos,
    getAllLikedPhotosForUser,
    getAllFavPhotosForUser,
    getLikesOfPhoto,
  } = React.useContext(GlobalContext);

  const [likeDataArray, setLikeDataArray] = useState([]);

  useEffect(() => {
    if (user?.favourite) {
      setLikeDataArray(
        photos.filter(({ photoId }) =>
          user?.favourite.some((d) => d === photoId)
        )
      );
    }
  }, [user?.favourite]);

  return (
    <ImageCards
      photos={likeDataArray}
      photoLikesMap={photoLikesMap}
      user={user}
      getAllLikedPhotosForUser={getAllLikedPhotosForUser}
      getAllFavPhotosForUser={getAllFavPhotosForUser}
      getLikesOfPhoto={getLikesOfPhoto}
    />
  );
};

export default MyCollectionPage;
