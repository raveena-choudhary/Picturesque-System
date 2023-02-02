import { IconButton, styled } from "@mui/material";

export const IconButtonStyled = styled(IconButton)(({ theme }) => ({
  backgroundColor: "#fff",
  borderRadius: "5px",
  "&:active": {
    backgroundColor: "#fff",
  },
  "&:hover": {
    backgroundColor: "#fff",
    opacity: 0.8,
  },
}));
