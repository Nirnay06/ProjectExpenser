import { Tag } from "antd";

const TagRender = (props) => {
  const { label, closable, onClose, options } = props;
  console.log(props);
  const onPreventMouseDown = (event) => {
    event.preventDefault();
    event.stopPropagation();
  };

  const color = options.filter((o) => o.label === label)[0].color;

  return (
    <Tag
      color={color}
      onMouseDown={onPreventMouseDown}
      closable={closable}
      onClose={onClose}
      style={{
        marginRight: 3,
      }}>
      {label}
    </Tag>
  );
};
export default TagRender;
