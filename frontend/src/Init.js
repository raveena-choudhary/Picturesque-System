import React, { useEffect } from "react";
import App from "./App";
import axios from "axios";

import {
  getUserObjectFromLocalStorage,
  setUserObjectFromLocalStorage,
} from "./utils/Util";
import endpoints from "./endpoints";

export const GlobalContext = React.createContext();

export const storeActions = {
  SET_USER: "SET_USER",
  SET_PHOTOS: "SET_PHOTOS",
  SET_USER_LIKED_PHOTOS: "SET_USER_LIKED_PHOTOS",
  SET_FAV_PHOTOS: "SET_FAV_PHOTOS",
  SET_LIKES_OF_ALL_PHOTOS: "SET_LIKES_OF_ALL_PHOTOS",
  SET_LIKE_PHOTO: "SET_LIKE_PHOTO",
  LOGOUT: "LOGOUT",
};

function store(state, action) {
  switch (action.type) {
    case storeActions.SET_USER: {
      setUserObjectFromLocalStorage(action.payload);
      return { ...state, user: action.payload };
    }
    case storeActions.SET_PHOTOS: {
      const { photos, photographers } = action.payload;
      return { ...state, photos, photographers };
    }
    case storeActions.SET_FAV_PHOTOS: {
      return {
        ...state,
        user: {
          ...state.user,
          favourite: action.payload,
        },
      };
    }
    case storeActions.SET_USER_LIKED_PHOTOS: {
      return {
        ...state,
        user: {
          ...state.user,
          like: action.payload,
        },
      };
    }
    case storeActions.LOGOUT: {
      setUserObjectFromLocalStorage(null);
      return { ...state, user: null };
    }
    case storeActions.SET_LIKES_OF_ALL_PHOTOS: {
      return { ...state, photoLikesMap: action.payload || {} };
    }
    case storeActions.SET_LIKE_PHOTO: {
      const { noOfLikes, photoId } = action.payload;
      const cpy = Object.assign({}, state.photoLikesMap);
      cpy[photoId] = noOfLikes;
      return { ...state, photoLikesMap: cpy };
    }

    default: {
      throw new Error(`Unhandled action type: ${action.type}`);
    }
  }
}

const Init = () => {
  const [state, dispatch] = React.useReducer(store, {
    user: getUserObjectFromLocalStorage(),
    photos: [],
    photographers: [],
    photoLikesMap: {},
  });

  const getAllPhotos = () => {
    axios.get(endpoints.GET_PHOTOGRAPHERS_WITH_PHOTOS).then(({ data }) => {
      console.log(data);

      const photos = data?.reduce((acc, { photos, ...rest }) => {
        const photosWithPhotograherData = photos.map((d) => ({
          ...d,
          ...rest,
        }));

        return [...acc, ...(photosWithPhotograherData || [])];
      }, []);

      const photographers = data;

      dispatch({
        type: storeActions.SET_PHOTOS,
        payload: { photos, photographers },
      });
    });
  };

  const getAllFavPhotosForUser = () => {
    axios
      .get(endpoints.GET_FAV_PHOTOS_OF_USER + state.user?.username)
      .then(({ data }) => {
        // console.log(data);
        dispatch({
          type: storeActions.SET_FAV_PHOTOS,
          payload: data?.map((d) => d.photoId) || [],
        });
      });
  };
  const getAllLikedPhotosForUser = () => {
    axios
      .get(endpoints.GET_LIKED_PHOTOS_OF_USER + state.user?.username)
      .then(({ data }) => {
        // console.log(data);
        dispatch({
          type: storeActions.SET_USER_LIKED_PHOTOS,
          payload: data?.map((d) => d.photoId) || [],
        });
      });
  };

  const getLikedPhotosMap = () => {
    axios.get(endpoints.GET_LIKES_OF_ALL_PHOTOS).then(({ data }) => {
      // console.log(data);
      dispatch({
        type: storeActions.SET_LIKES_OF_ALL_PHOTOS,
        payload: data || {},
      });
    });
  };

  const getLikesOfPhoto = (photoId) => {
    axios.get(endpoints.GET_LIKES_OF_PHOTO + photoId).then(({ data }) => {
      // console.log(data);
      dispatch({
        type: storeActions.SET_LIKE_PHOTO,
        payload: { photoId, noOfLikes: data || 0 },
      });
    });
  };

  useEffect(() => {
    if (state.user?.username) {
      getAllPhotos();
      getAllFavPhotosForUser();
      getAllLikedPhotosForUser();
      getLikedPhotosMap();
    }
  }, [state.user?.username]);

  return (
    <>
      <GlobalContext.Provider
        value={{
          state,
          dispatch,
          getAllPhotos,
          getAllFavPhotosForUser,
          getAllLikedPhotosForUser,
          getLikesOfPhoto,
        }}
      >
        <App />
      </GlobalContext.Provider>
    </>
  );
};

export default Init;
