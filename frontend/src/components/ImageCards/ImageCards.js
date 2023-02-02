import React, { useEffect, useState } from "react";
import ImageCard from "./ImageCard/ImageCard";
import photo from "../../assets/photo.jpeg";
import { Box } from "@mui/material";
import axios from "axios";
import { GlobalContext } from "./../../Init";
import endpoints from "./../../endpoints";

const ImageCards = ({
  photos = [],
  user,
  getAllLikedPhotosForUser,
  getAllFavPhotosForUser,
  getLikesOfPhoto,
  photoLikesMap,
}) => {
  const handleLikeButton = (photoId, isAlreadyLiked) => {
    const url = isAlreadyLiked
      ? endpoints.REMOVE_LIKED_PHOTOS_FROM_USER
      : endpoints.ADD_LIKED_PHOTOS_FROM_USER;
    return axios
      .post(url + "username=" + user.username + "&photoId=" + photoId)
      .then((data) => {
        getAllLikedPhotosForUser();
        getLikesOfPhoto(photoId);
      });
  };
  const handleBookmarkButton = (photoId, isAlreadyBookmarked) => {
    const url = isAlreadyBookmarked
      ? endpoints.REMOVE_FAV_PHOTOS_FROM_USER
      : endpoints.ADD_FAV_PHOTOS_FROM_USER;
    return axios
      .post(url + "username=" + user.username + "&photoId=" + photoId)
      .then((data) => {
        getAllFavPhotosForUser();
      });
  };

  return (
    <Box
      sx={{
        display: "grid",
        gridTemplateColumns: "1fr 1fr 1fr",
        gap: "30px",
        overflow: "auto",
      }}
    >
      {photos.map(
        ({
          avgColor,
          imageMediumSize,
          photoId,
          photographerName,
          photographerId,
          imageOrignalSize,
          title,
        }) => (
          <ImageCard
            key={photoId}
            isLiked={user.like?.includes(photoId)}
            isBookmarked={user.favourite?.includes(photoId)}
            thumbnailSrc={imageMediumSize}
            orignalSrc={imageOrignalSize}
            noOfLikes={photoLikesMap?.[photoId]}
            photographerName={photographerName}
            imageBgColor={avgColor}
            title={title}
            photoId={photoId}
            handleLikeButton={handleLikeButton}
            handleBookmarkButton={handleBookmarkButton}
          />
        )
      )}
    </Box>
  );
};

export default ImageCards;
