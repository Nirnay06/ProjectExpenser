import { Breadcrumbs, Link, Typography } from "@mui/joy";
import { ChevronRightRounded as ChevronRightRoundedIcon, HomeRounded as HomeRoundedIcon } from "@mui/icons-material";

const BreadCrumbs = () => {
  return (
    <Breadcrumbs size="sm" aria-label="breadcrumbs" separator={<ChevronRightRoundedIcon fontSize="sm" />} sx={{ pl: 0 }}>
      <Link underline="none" color="neutral" href="#some-link" aria-label="Home">
        <HomeRoundedIcon />
      </Link>
      <Link underline="hover" color="neutral" href="#some-link" sx={{ fontSize: 12, fontWeight: 500 }}>
        Dashboard
      </Link>
      <Typography color="primary" sx={{ fontWeight: 500, fontSize: 12 }}>
        Expenses
      </Typography>
    </Breadcrumbs>
  );
};

export default BreadCrumbs;
