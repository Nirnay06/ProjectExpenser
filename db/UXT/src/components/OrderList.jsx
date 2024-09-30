import * as React from 'react';
import Box from '@mui/joy/Box';
import Button from '@mui/joy/Button';
import Typography from '@mui/joy/Typography';
import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown';
import OrderTable from './ExpenseTable';

export default function OrderList() {
  return (
    <Box sx={{ display: 'flex', alignItems: 'center', py: 1, pr: 1 }}>
      <Typography
        level="body-lg"
        sx={{ flex: 1 }}
      >
        Orders
      </Typography>
      <Box sx={{ display: 'flex', gap: 1, alignItems: 'center' }}>
        <Button
          size="sm"
          variant="plain"
          color="primary"
          endDecorator={<ArrowDropDownIcon fontSize="small" />}
        >
          Sort
        </Button>
        <Button size="sm" variant="outlined" color="neutral">
          Group
        </Button>
      </Box>
    </Box>
  );
}
