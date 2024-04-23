import { Outlet } from "react-router-dom";

export default function BasicLayout(props) {
  return (
    <div>
      <Outlet />
    </div>
  );
}
