import useHttp from "../hooks/useHttp";
import { Col, Row } from "antd";
import { TreeNode } from "antd/lib/tree-select";
import icons from "./../assets/sprite.svg";

const useCategoryService = () => {
  const { sendRequest } = useHttp();

  const getCategoryTreeNodes = (CategoryList) => {
    if (CategoryList && CategoryList.length > 0) {
      return CategoryList.map((c) => {
        return (
          <TreeNode
            value={c.identifier}
            title={
              <Row align="middle">
                <Col
                  className="dot dot-icon"
                  style={{
                    backgroundColor: c.color,
                    marginRight: "5px",
                  }}>
                  <svg className={`icon icon-${c.icon}`}>
                    <use href={`${icons}#icon-${c.icon}`}></use>
                  </svg>
                </Col>
                <Col>
                  <span>{c.title}</span>
                </Col>
              </Row>
            }
            selectable={c.selectable}
            key={c.value}>
            {getCategoryTreeNodes(c.children)}
          </TreeNode>
        );
      });
    }
  };

  const fetchUserCategory = (setCategoryList) => {
    sendRequest({ url: "/api/category/findCategoriesForRecord" }, (data) => {
      setCategoryList(data);
    });
  };
  return { CategoryService: { getCategoryTreeNodes, fetchUserCategory } };
};
export default useCategoryService;
