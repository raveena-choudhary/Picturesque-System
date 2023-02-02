import React, { useState } from "react";
import { Box } from "@mui/material";
import Logo from "./../ui/Logo/Logo";
import User from "../components/User/User";
// import Aside from "./Aside/Aside";
import Aside from "../components/Aside/Aside";
import AutoAwesomeMosaicOutlinedIcon from "@mui/icons-material/AutoAwesomeMosaicOutlined";
import AccountBoxOutlinedIcon from "@mui/icons-material/AccountBoxOutlined";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import BookmarksOutlinedIcon from "@mui/icons-material/BookmarksOutlined";
import ExplorePage from "./ExplorePage/ExplorePage";
import PhotographerPage from "./PhotographerPage/PhotographerPage";
import LikedPage from "./LikedPage/LikedPage";
import MyCollectionPage from "./MyCollectionPage/MyCollectionPage";

export const pageToSectionMap = {
  EXPLORE: {
    title: "Explore",
    key: "EXPLORE",
    icon: AutoAwesomeMosaicOutlinedIcon,
  },
  PHOTOGRAPHER: {
    title: "Photographers",
    key: "PHOTOGRAPHER",
    icon: AccountBoxOutlinedIcon,
  },
  LIKED: {
    title: "Liked Photos",
    key: "LIKED",
    icon: FavoriteBorderIcon,
  },
  SAVED: {
    title: "My Collection",
    key: "SAVED",
    icon: BookmarksOutlinedIcon,
  },
};

const getCurrentPage = (key) => {
  switch (key) {
    case pageToSectionMap.EXPLORE:
      return <ExplorePage />;
    case pageToSectionMap.PHOTOGRAPHER:
      return <PhotographerPage />;
    case pageToSectionMap.LIKED:
      return <LikedPage />;
    case pageToSectionMap.SAVED:
      return <MyCollectionPage />;

    default:
      return null;
  }
};

const Pages = () => {
  const [currentPage, setCurrentPage] = useState(pageToSectionMap.EXPLORE);
  return (
    <Box
      sx={{
        // padding: "10px",
        display: "flex",
        height: "100%",
        gap: "8px",
      }}
    >
      <Aside
        activeLink={currentPage.key}
        setActiveLink={(key) => setCurrentPage(pageToSectionMap[key])}
      />
      <Box
        sx={{
          minWidth: 0,
          flex: 1,

          height: "100%",
          textAlign: "left",
          padding: "10px",
          display: "flex",
          flexDirection: "column",
        }}
      >
        <Box
          sx={{
            fontSize: "1.8rem",
            color: (theme) => theme.palette.primary.main,
            fontWeight: 500,
            marginBottom: "15px",
          }}
        >
          {currentPage.title}
        </Box>
        {getCurrentPage(currentPage)}
        {/* {currentPage==pageToSectionMap.EXPLORE} */}
        {/* {currentPage==pageToSectionMap.EXPLORE} */}
        {/* {currentPage==pageToSectionMap.EXPLORE} */}
      </Box>
    </Box>
  );
};

export default Pages;
