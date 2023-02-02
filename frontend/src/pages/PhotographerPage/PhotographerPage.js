import React from "react";
import PhotographerCards from "./../../components/PhotographerCards/PhotographerCards";

import { GlobalContext } from "./../../Init";

const PhotographerPage = () => {
  const {
    state: { photographers },
  } = React.useContext(GlobalContext);

  return (
    <>
      <PhotographerCards photographers={photographers} />
    </>
  );
};

export default PhotographerPage;
