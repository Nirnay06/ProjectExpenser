import { Box } from "@mui/joy";
import Header from "../components/Header";
import Sidebar from "../components/ExpenseSidebar";
import BreadCrumb from "../components/BreadCrumb";

const DashboardLayout = (props) => {
  return (
    <Box sx={{ display: "flex", minHeight: "100dvh" }}>
      <Header />
      <Sidebar />
      <Box
        component="main"
        className="MainContent"
        sx={{
          px: { xs: 2, md: 6 },
          pt: {
            xs: "calc(12px + var(--Header-height))",
            sm: "calc(12px + var(--Header-height))",
            md: 3,
          },
          pb: { xs: 2, sm: 2, md: 3 },
          flex: 1,
          display: "flex",
          flexDirection: "column",
          minWidth: 0,
          height: "100dvh",
          gap: 1,
        }}
      >
        <Box sx={{ display: "flex", alignItems: "center" }}>{/* <BreadCrumb /> */}</Box>
        {props.children}
        {/* <OrderTable /> */}
        {/* <OrderList /> */}
      </Box>
    </Box>
  );
};

export default DashboardLayout;
