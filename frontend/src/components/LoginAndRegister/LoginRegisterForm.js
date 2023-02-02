import * as React from "react";
import Box from "@mui/material/Box";
import IconButton from "@mui/material/IconButton";
import Input from "@mui/material/Input";
import FilledInput from "@mui/material/FilledInput";
import OutlinedInput from "@mui/material/OutlinedInput";
import InputLabel from "@mui/material/InputLabel";
import InputAdornment from "@mui/material/InputAdornment";
import FormHelperText from "@mui/material/FormHelperText";
import FormControl from "@mui/material/FormControl";
import TextField from "@mui/material/TextField";
import Visibility from "@mui/icons-material/Visibility";
import VisibilityOff from "@mui/icons-material/VisibilityOff";
import { useState } from "react";
import { Alert, AlertTitle, Button } from "@mui/material";
import { setUserObjectFromLocalStorage } from "./../../utils/Util";

const LoginRegisterForm = ({ loginUser, registerUser }) => {
  const [data, setData] = useState({ username: "", password: "" });
  const [showPassword, setshowPassword] = useState(false);
  const [isLogin, setIsLogin] = useState(true);
  const [APIMessage, setAPIMessage] = useState({
    type: "",
    message: "",
  });

  const handleChange = (prop) => (event) => {
    setData((prev) => ({ ...prev, [prop]: event.target.value }));
  };

  const handleLoginRegisterState = () => {
    setIsLogin((prev) => !prev);
  };

  const handleClickShowPassword = () => {
    setshowPassword((prev) => !prev);
  };

  const isDisabled = () => {
    return data.username === "" || data.password === "";
  };

  const handleSubmit = () => {
    if (isLogin) {
      loginUser(data.username, data.password)
        .then((data) => {
          console.log(data);
        })
        .catch((data) => {
          console.log(data);
          setAPIMessage(data.response?.data);
        })
        .finally(() => {});
    } else {
      registerUser(data.username, data.password)
        .then((data) => {
          console.log(data);
          setAPIMessage(data.data);
        })
        .catch((data) => {
          console.log(data);
          setAPIMessage(data.response?.data);
        })
        .finally(() => {});
    }
  };

  return (
    <>
      {APIMessage.message && (
        <Box sx={{ marginBottom: "20px", width: "100%" }}>
          <Alert
            variant="filled"
            severity={APIMessage.type}
            sx={{
              // fontWeight: 600,
              padding: "5px",
              display: "flex",
              alignItems: "center",
              "& .MuiAlert-message": {
                padding: "5px",
              },
              "& .MuiTypography-root": {
                marginBottom: 0,
                padding: "0px",
                marginTop: 0,
              },
            }}
          >
            <AlertTitle sx={{ fontSize: "0.8rem" }}>
              {APIMessage.message}
            </AlertTitle>
          </Alert>
        </Box>
      )}

      <Box sx={{ fontSize: "1.5rem", marginBottom: "20px" }}>
        {isLogin ? "Sign In" : "Sign Up"}
      </Box>
      <FormControl sx={{ m: 1, width: "25ch" }} variant="outlined">
        <InputLabel htmlFor="outlined-adornment-password">Username</InputLabel>
        <OutlinedInput
          id="outlined-adornment-username"
          value={data.username}
          onChange={handleChange("username")}
          label="Username"
        />
      </FormControl>

      <FormControl sx={{ m: 1, width: "25ch" }} variant="outlined">
        <InputLabel htmlFor="outlined-adornment-password">Password</InputLabel>
        <OutlinedInput
          id="outlined-adornment-password"
          type={showPassword ? "text" : "password"}
          value={data.password}
          onChange={handleChange("password")}
          endAdornment={
            <InputAdornment position="end">
              <IconButton
                aria-label="toggle password visibility"
                onClick={handleClickShowPassword}
                edge="end"
                // sx={{ color: "#fff" }}
              >
                {showPassword ? <VisibilityOff /> : <Visibility />}
              </IconButton>
            </InputAdornment>
          }
          label="Password"
        />
      </FormControl>
      <Button
        variant="contained"
        sx={{
          backgroundColor: (theme) => theme.palette.primary.main,
          color: "#aaa",
          textTransform: "initial",
          margin: "15px 0px 30px 0px",
        }}
        disabled={isDisabled()}
        onClick={handleSubmit}
      >
        {isLogin ? "Login" : "Create Account"}
      </Button>

      <Box
        sx={{
          fontSize: "0.8rem",
          color: "#333",
          cursor: "pointer",
          "&:hover": {
            opacity: 0.8,
          },
        }}
        onClick={handleLoginRegisterState}
      >
        {isLogin
          ? "Click here to create a new account"
          : "Already have an account? click to login"}
      </Box>
    </>
  );
};

export default LoginRegisterForm;
