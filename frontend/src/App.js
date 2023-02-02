// import logo from "./logo.svg";
import "./App.css";
// import Card from "./ui/Card/card";
import ImageCard from "./components/ImageCards/ImageCard/ImageCard";

import ImageCards from "./components/ImageCards/ImageCards";
import PhotographerCards from "./components/PhotographerCards/PhotographerCards";
import Pages from "./pages/Pages";
import LoginAndRegister from "./components/LoginAndRegister/LoginAndRegister";
import { createContext, useContext } from "react";

import { GlobalContext } from "./Init";
import { useEffect } from "react";

function App() {
  const { state } = useContext(GlobalContext);

  return (
    <div className="App" style={{ height: "100vh", width: "100%" }}>
      {state.user ? <Pages /> : <LoginAndRegister />}
      {/* <header className="App-header"></header> */}
      {/* <ImageCards /> */}
      {/* <PhotographerCards /> */}
      {/* */}
    </div>
  );
}

export default App;
