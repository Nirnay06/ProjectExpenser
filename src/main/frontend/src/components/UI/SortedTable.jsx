import { DndContext } from "@dnd-kit/core";
import { restrictToVerticalAxis } from "@dnd-kit/modifiers";
import { SortableContext, useSortable, verticalListSortingStrategy } from "@dnd-kit/sortable";
import { CSS } from "@dnd-kit/utilities";
import { MenuOutlined } from "@ant-design/icons";
import { Table, ConfigProvider } from "antd";
import React from "react";
const SortedTable = ({ list, sortProperty, columns, setUpdatedData, pagination, className, ...props }) => {
  const Row = ({ children, ...props }) => {
    const { attributes, listeners, setNodeRef, setActivatorNodeRef, transform, transition, isDragging } = useSortable({
      id: props["data-row-key"],
    });
    const style = {
      ...props.style,
      transform: CSS.Transform.toString(
        transform && {
          ...transform,
          scaleY: 1,
        }
      ),
      transition,
      ...(isDragging && sortProperty
        ? {
            position: "relative",
            zIndex: 9999,
          }
        : {}),
    };
    return (
      <tr {...props} ref={setNodeRef} style={style} {...attributes}>
        {React.Children.map(children, (child) => {
          if (child.key === "sort") {
            return React.cloneElement(child, {
              children: (
                <MenuOutlined
                  ref={setActivatorNodeRef}
                  style={{
                    touchAction: "none",
                    cursor: "move",
                  }}
                  {...listeners}
                />
              ),
            });
          }
          return child;
        })}
      </tr>
    );
  };
  const onDragEnd = ({ active, over }) => {
    if (active.id !== over?.id) {
      setUpdatedData((previous) => {
        const activeIndex = previous.findIndex((i) => i[sortProperty] === active.id);
        const overIndex = previous.findIndex((i) => i[sortProperty] === over?.id);
        let updated = [...previous];
        updated[activeIndex][sortProperty] = over.id;
        updated[overIndex][sortProperty] = active.id;
        return updated;
      });
    }
  };

  return (
    <DndContext modifiers={[restrictToVerticalAxis]} onDragEnd={onDragEnd}>
      <SortableContext items={list.map((i) => i[sortProperty])} strategy={verticalListSortingStrategy}>
        <ConfigProvider
          theme={{
            token: {
              colorBgContainer: "transparent",
            },
          }}
        >
          <Table
            locale={{ emptyText: "No Data found" }}
            components={{
              body: {
                row: Row,
              },
            }}
            rowKey="displayOrder"
            columns={columns}
            dataSource={list}
            pagination={pagination}
            className={`sortedTable ${className}`}
            {...props}
          />
        </ConfigProvider>
      </SortableContext>
    </DndContext>
  );
};

export default SortedTable;
