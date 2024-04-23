import BasicLayout from "./Layouts/BasicLayout";
import HomePage from "./pages/HomePage";
import Login from "./pages/Login";

export default function Routers(RedirectPath) {
  let routes = [
    {
      path: "/",
      element: <BasicLayout RedirectPath={RedirectPath} />,
      children: [
        {
          index: true,
          path: "/",
          element: <Login RedirectPath={RedirectPath} />,
          children: [],
        },
        {
          path: "/weatherTracker",
          element: <HomePage RedirectPath={RedirectPath} />,
          children: [],
        },
      ],
    },
  ];

  return routes;
}
