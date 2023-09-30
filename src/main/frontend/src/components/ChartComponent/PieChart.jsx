import ReactDOM from 'react-dom';
import { Pie, measureTextWidth } from '@ant-design/plots';
import Paragraph from 'antd/es/typography/Paragraph';

const PieChart = ({formatter =(v)=> v , ...props}) => {
  function renderStatistic( text, style) {
    return <Paragraph ellipsis={true}  style={{...style, marginBottom : 0, maxWidth : '90px'}}>{text}</Paragraph>;
  }
  const config = {
    appendPadding: 10,
    data : props.data,
    legend : {visible :false},
    angleField: 'value',
    colorField: 'category',
    radius: 1,
    innerRadius: 0.64,
    meta: {
      value: {
        formatter: formatter,
      },
    },
    label: {
      type: 'inner',
      offset: '-50%',
      style: {
        textAlign: 'center',
      },
      autoRotate: false,
      content: (obj) => formatter(obj.value),
    },
    statistic: {
      title: {
        offsetY: -4,
        customHtml: (container, view, datum) => {
          const text = datum ? datum[props.config.colorField] : 'Total';
          return renderStatistic( text, {
            fontSize: '12px',
          });
        },
      },
      content: {
        offsetY: 4,
        customHtml: (container, view, datum, data) => {
         const text = datum ? `${formatter(datum.value)}` : `${formatter(data.reduce((r, d) => r + d.value, 0))}`;
          return renderStatistic(text, {
            fontSize: '12px',
          });
        },
      },
    },
    interactions: [
      {
        type: 'element-selected',
      },
      {
        type: 'element-active',
      },
      {
        type: 'pie-statistic-active',
      },
    ],
    ...props.config
  };
  return <Pie {...config} />;
};

export default PieChart;