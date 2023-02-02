import React from "react";
import { Box } from "@mui/material";
import PhotographerCard from "./PhotographerCard/PhotographerCard";

const PhotographerCards = ({ photographers = [] }) => {
  return (
    <Box
      sx={{
        display: "grid",
        gridTemplateColumns: "1fr 1fr",
        gap: "30px",
        height: "100%",
        overflow: "auto",
        padding: "10px",
        margin: "-10px",
      }}
    >
      {photographers.map(({ photographerName, photographerId, photos }) => (
        <PhotographerCard
          key={photographerId}
          photos={photos}
          photographerName={photographerName}
        />
      ))}
    </Box>
  );
};

export default PhotographerCards;
