import React from "react";
import logoURL from "../../assets/logo.png";
import { Box } from "@mui/material";

const Logo = () => {
  return (
    <Box
      sx={{ height: "auto", width: "70%" }}
      component={"img"}
      src={logoURL}
    />
  );
};

export default Logo;
