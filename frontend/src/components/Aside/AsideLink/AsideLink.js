import React from "react";
import { Box, lighten } from "@mui/material";

const AsideLink = ({ title = "", isActive = false, onClick, Icon }) => {
  return (
    <Box
      onClick={onClick}
      sx={{
        display: "flex",
        alignItems: "center",
        padding: "12px 10px",
        gap: "5px",
        backgroundColor: (theme) =>
          isActive && lighten(theme.palette.primary.main, 0.1),
        cursor: "pointer",
        "&:hover": {
          backgroundColor: (theme) =>
            !isActive && lighten(theme.palette.primary.main, 0.2),
        },
      }}
    >
      <Icon />
      {title}
    </Box>
  );
};

export default AsideLink;
