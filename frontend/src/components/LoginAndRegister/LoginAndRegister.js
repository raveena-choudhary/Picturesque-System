import React from "react";
import { Box } from "@mui/material";
import LoginRegisterForm from "./LoginRegisterForm";
import axios from "axios";
import { storeActions } from "../../Init";
import { GlobalContext } from "./../../Init";
import logoURL from "../../assets/logo_outlined.png";
import endpoints from "./../../endpoints";

const LoginAndRegister = () => {
  const { dispatch } = React.useContext(GlobalContext);

  const getUser = (username, password) => {
    return axios
      .get(endpoints.GET_USER_DATA + username + "&password=" + btoa(password))
      .then((data) => {
        if (data.data) {
          dispatch({ type: storeActions.SET_USER, payload: data.data });
        }
        return data?.data;
      });
  };

  const loginUser = (username, password) => {
    return axios
      .get(endpoints.VALIDATE_USER + username + "&password=" + btoa(password))
      .then((data) => {
        getUser(username, password);
      });
  };

  const registerUser = (username, password) => {
    return axios.post(
      endpoints.REGISTER_USER + username + "&password=" + btoa(password)
    );
    // .then(() => {});
  };

  return (
    <Box
      sx={{
        width: "100%",
        height: "100%",
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
      }}
    >
      <Box
        sx={{
          height: "auto",
          width: "200px",
          marginTop: "-80px",
          marginBottom: "10px",
        }}
        component={"img"}
        src={logoURL}
      />

      <Box
        sx={{
          width: "max-content",
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          justifyContent: "center",
          border: (theme) => "3px solid" + theme.palette.primary.main,
          borderRadius: (theme) => theme.shape.borderRadius,
          padding: "20px",
          "& *": {
            fontFamily: "PlusJakartaDisplay-Regular",
          },
          //   "& label": {
          //     color: "#fff",
          //   },
          //   "& label.Mui-focused": {
          //     color: "#fff",
          //   },
          //   "& input": {
          //     color: "#fff",
          //   },
          //   "& .MuiInput-underline:after": {
          //     borderBottomColor: "#fff",
          //   },
          //   "& .MuiOutlinedInput-root": {
          //     "& fieldset": {
          //       borderColor: "#fff",
          //     },
          //     "&:hover fieldset": {
          //       borderColor: "#fff",
          //     },
          //     "&.Mui-focused fieldset": {
          //       borderColor: "#fff",
          //     },
          //   },
        }}
      >
        <LoginRegisterForm loginUser={loginUser} registerUser={registerUser} />
      </Box>
    </Box>
  );
};

export default LoginAndRegister;
