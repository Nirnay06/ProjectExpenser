import { Tag } from "antd";
import icons from "../../assets/sprite.svg";
const TagRender = (props) => {
  const { label, closable, onClose, options, fieldKey } = props;
  const onPreventMouseDown = (event) => {
    event.preventDefault();
    event.stopPropagation();
  };

  const color = options?.filter((o) => o[`${fieldKey}`] === label)[0]?.color;
  const icon = options?.filter((o) => o[`${fieldKey}`] === label)[0]?.icon;
  return (
    <Tag
      color={color}
      icon={
        icon ? (
          <svg className={`icon icon-${icon}`}>
            <use href={`${icons}#icon-${icon}`}></use>
          </svg>
        ) : (
          <></>
        )
      }
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
