import { styled } from "@mui/material";
import React from "react";

const CardStyled = styled("div")(({ theme }) => ({
  // color: "#fff",
  // backgroundColor: "#18192f",
  borderRadius: "16px",
  // boxShadow: theme.shadows[2],
  color: "#777",
  // "&:hover": {
  //   backgroundColor: purple[700],
  // },
}));

const Card = ({ children, sx }) => {
  return <CardStyled sx={sx}> {children}</CardStyled>;
};

export default Card;
