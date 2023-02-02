import React, { useEffect, useState } from "react";
import { GlobalContext } from "./../../Init";
import ImageCards from "./../../components/ImageCards/ImageCards";

const LikedPage = () => {
  const {
    state: { photos, user, photoLikesMap },
    getAllPhotos,
    getAllLikedPhotosForUser,
    getAllFavPhotosForUser,
    getLikesOfPhoto,
  } = React.useContext(GlobalContext);

  const [likeDataArray, setLikeDataArray] = useState([]);

  useEffect(() => {
    if (user?.like) {
      setLikeDataArray(
        photos.filter(({ photoId }) => user?.like.some((d) => d === photoId))
      );
    }
  }, [user?.like]);

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

export default LikedPage;
