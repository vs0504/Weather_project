import { useNavigate, useRoutes } from "react-router-dom";
import Routers from "./router";
import weatherImg from "./assets/images/weatherBackground.jpg";
//style={{ backgroundImage: `url(${weatherImg})`, height: "100vh" }}

function App() {
  let navigate = useNavigate();
  const Redirectpath = (path) => {
    navigate(path, { replace: true });
  };
  // The useRoutes() hook allows you to define your routes as JavaScript objects
  // instead of <Routes> and <Route> elements. This is really just a style
  // preference for those who prefer to not use JSX for their routes config.
  let element = useRoutes(Routers(Redirectpath));
  return <div>{element}</div>;
}

export default App;
