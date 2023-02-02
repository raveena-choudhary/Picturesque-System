import { Avatar, Box, Tooltip } from "@mui/material";
import React from "react";
import Card from "../../../ui/Card/Card";
import FavoriteIcon from "@mui/icons-material/Favorite";
import photo from "../../../assets/photo.jpeg";
import ImageCard from "./../../ImageCards/ImageCard/ImageCard";

const PhotographerCard = ({ photographerName, photos = [] }) => {
  return (
    <Card
      sx={{
        display: "flex",
        flexDirection: "column",
        width: "100%",
        height: "300px",
        padding: "15px",
        gap: "10px",
        transition: "100ms box-shadow    ease-in-out",
        // border: "none",
        boxShadow:
          " 0px 0.8px 2.1px -7px rgba(0, 0, 0, 0.026), 0px 1.9px 5.1px -7px rgba(0, 0, 0, 0.036), 0px 3.6px 9.6px -7px rgba(0, 0, 0, 0.043), 0px 6.5px 17.2px -7px rgba(0, 0, 0, 0.05), 0px 12.1px 32.2px -7px rgba(0, 0, 0, 0.059), 0px 29px 77px -7px rgba(0, 0, 0, 0.08)",
        // "&:hover": {
        //   cursor: "pointer",
        //   outline: "1px solid #ddd",
        //   boxShadow:
        //     "0px 2.4px 2.1px -7px rgba(0, 0, 0, 0.045), 0px 5.7px 5.1px -7px rgba(0, 0, 0, 0.063), 0px 10.8px 9.6px -7px rgba(0, 0, 0, 0.076), 0px 19.2px 17.2px -7px rgba(0, 0, 0, 0.088), 0px 35.9px 32.2px -7px rgba(0, 0, 0, 0.103), 0px 86px 77px -7px rgba(0, 0, 0, 0.14) ",
        // },
      }}
    >
      <Box
        sx={{
          display: "flex",
          alignItems: "center",
          gap: "5px",
        }}
      >
        <Avatar sx={{ width: 50, height: 50, fontSize: "2rem" }}>
          {photographerName.charAt(0)}
        </Avatar>
        <Box sx={{ fontSize: "1rem" }}>{photographerName}</Box>
      </Box>

      <Box
        sx={{
          display: "flex",
          justifyContent: "space-between",
          height: "auto",
          backgroundColor: "transparent",
          gap: "10px",
          overflowX: "auto",
          overflowY: "hidden",
          flex: 1,
          minHeight: 0,
          // color: "#fff",
        }}
      >
        {photos.map(({ avgColor, imageMediumSize, photoId }) => (
          <ImageCard
            showOnlyImage={true}
            sxCard={{ height: "100%", flex: "1 0 32%" }}
            key={photoId}
            thumbnailSrc={imageMediumSize}
            imageBgColor={avgColor}
          />
        ))}
      </Box>
    </Card>
  );
};

export default PhotographerCard;
