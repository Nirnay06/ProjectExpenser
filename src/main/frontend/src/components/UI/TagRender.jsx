import { Tag } from "antd";

const TagRender = (props) => {
  const { label, value, closable, onClose, options } = props;

  const onPreventMouseDown = (event) => {
    event.preventDefault();
    event.stopPropagation();
  };

  const color = options.filter((o) => o.value === value)[0].color;

  return (
    <Tag
      color={color}
      onMouseDown={onPreventMouseDown}
      closable={closable}
      onClose={onClose}
      style={{
        marginRight: 3,
      }}
    >
      {label}
    </Tag>
  );
};
export default TagRender;
