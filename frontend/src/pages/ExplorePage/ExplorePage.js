import React from "react";
import { GlobalContext, storeActions } from "../../Init";
import ImageCards from "./../../components/ImageCards/ImageCards";

const ExplorePage = () => {
  const {
    state: { photos, user, photoLikesMap },
    getAllPhotos,
    getAllLikedPhotosForUser,
    getAllFavPhotosForUser,
    getLikesOfPhoto,
    dispatch,
  } = React.useContext(GlobalContext);

  return (
    <ImageCards
      photos={photos}
      photoLikesMap={photoLikesMap}
      user={user}
      getAllLikedPhotosForUser={getAllLikedPhotosForUser}
      getAllFavPhotosForUser={getAllFavPhotosForUser}
      getLikesOfPhoto={getLikesOfPhoto}
    />
  );
};

export default ExplorePage;
