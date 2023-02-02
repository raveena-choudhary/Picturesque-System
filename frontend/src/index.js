import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
// import App from "./App";
import reportWebVitals from "./reportWebVitals";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import "./assets/fonts/PlusJakartaText-Light.otf";
import "./assets/fonts/PlusJakartaDisplay-Regular.otf";
import "./assets/fonts/PlusJakartaDisplay-Medium.otf";
import "./assets/fonts/PlusJakartaDisplay-Bold.otf";
import Init from "./Init";

const theme = createTheme({
  palette: {
    primary: { main: "#151f32" },

    colors: {
      pink: "#ea4c89",
      textWhite: "#dddddd",
    },
  },
});

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  // <React.StrictMode>
    <ThemeProvider theme={theme}>
      <Init />
    </ThemeProvider>
  // </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
