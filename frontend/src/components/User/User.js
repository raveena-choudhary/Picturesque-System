import React, { useContext } from "react";
import { GlobalContext } from "./../../Init";
import { Avatar, Box, Tooltip } from "@mui/material";

const User = () => {
  const { state: { user: { username } = {} } = {} } = useContext(GlobalContext);
  return (
    <Box
      sx={{
        display: "flex",
        alignItems: "center",
        gap: "5px",
        borderRadius: "10px",
        border: " 2px solid #fff",
        margin: "30px 10px",
        padding: "10px",
      }}
    >
      <Avatar
        sx={{
          width: 40,
          height: 40,
          fontSize: "1.2rem",
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
          lineHeight: "initial",
          backgroundColor: "#dd2600",
        }}
      >
        {username.charAt(0)}
      </Avatar>
      <Tooltip disableInteractive title={username}>
        <Box
          sx={{
            fontSize: "1rem",
            textOverflow: "ellipsis",
            whiteSpace: "nowrap",
            overflow: "hidden",
          }}
        >
          {username}
        </Box>
      </Tooltip>
    </Box>
  );
};

export default User;
