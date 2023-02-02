const GET_PHOTOGRAPHERS_WITH_PHOTOS =
  "/photo-api/get-photographers-with-photos";
const GET_FAV_PHOTOS_OF_USER = "/photo-api/get-fav-photos?username=";
const GET_LIKED_PHOTOS_OF_USER = "/photo-api/get-liked-photos?username=";
const GET_LIKES_OF_ALL_PHOTOS = "/photo-api/get-likes-of-all-photos";
const GET_LIKES_OF_PHOTO = "/photo-api/get-likes-of-photo?photoId=";
const REMOVE_LIKED_PHOTOS_FROM_USER = "/photo-api/remove-liked-photos?";
const ADD_LIKED_PHOTOS_FROM_USER = "/photo-api/add-liked-photos?";
const REMOVE_FAV_PHOTOS_FROM_USER = "/photo-api/remove-fav-photos?";
const ADD_FAV_PHOTOS_FROM_USER = "/photo-api/add-fav-photos?";
const GET_USER_DATA = "/photo-api/get-user?username=";
const VALIDATE_USER = "/photo-api/validate-user?username=";
const REGISTER_USER = "/photo-api/add-user?username=";

const endpoints = {
  GET_PHOTOGRAPHERS_WITH_PHOTOS,
  GET_FAV_PHOTOS_OF_USER,
  GET_LIKED_PHOTOS_OF_USER,
  GET_LIKES_OF_ALL_PHOTOS,
  GET_LIKES_OF_PHOTO,
  REMOVE_LIKED_PHOTOS_FROM_USER,
  ADD_LIKED_PHOTOS_FROM_USER,
  REMOVE_FAV_PHOTOS_FROM_USER,
  ADD_FAV_PHOTOS_FROM_USER,
  GET_USER_DATA,
  VALIDATE_USER,
  REGISTER_USER,
};

export default endpoints;
