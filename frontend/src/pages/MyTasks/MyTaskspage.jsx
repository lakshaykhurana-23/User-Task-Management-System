import React, { useEffect, useState } from "react";
import { myTasks } from "../../api/mytasks";
import { Table, Select, message } from "antd";

const { Option } = Select;

function AllTaskPage() {
  const [taskdata, setTaskdata] = useState([]);
  const [loading, setLoading] = useState(false);

  const fetchTasks = async () => {
    setLoading(true);
    try {
      const response = await myTasks();
      const data = response.data;

      const formattedData = data.map((task) => ({
        key: task.id,
        Id: task.id,
        Title: task.title,
        Description: task.description,
        Priority: task.priority,
        Status: task.status,
      }));

      setTaskdata(formattedData);
    } catch (err) {
      message.error("Failed to load tasks");
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const columns = [
    {
      title: "ID",
      dataIndex: "Id",
      width: 60,
    },
    {
      title: "Title",
      dataIndex: "Title",
      width: 150,
    },
    {
      title: "Description",
      dataIndex: "Description",
      width: 250,
    },
    
    {
      title: "Priority",
      dataIndex: "Priority",
      width: 120,
      render: (value, record) => (
        <Select
          value={value}
          style={{ width: "100%" }}
          onChange={(val) =>
            handleTaskChange(record.Id, { priority: val })
          }
        >
          <Option value="HIGH">HIGH</Option>
          <Option value="MEDIUM">MEDIUM</Option>
          <Option value="LOW">LOW</Option>
        </Select>
      ),
    },
    {
      title: "Status",
      dataIndex: "Status",
      width: 150,
      render: (value, record) => (
        <Select
          value={value}
          style={{ width: "100%" }}
          onChange={(val) =>
            handleTaskChange(record.Id, { status: val })
          }
        >
          <Option value="TODO">TODO</Option>
          <Option value="INPROGRESS">INPROGRESS</Option>
          <Option value="TESTING">TESTING</Option>
          <Option value="COMPLETED">COMPLETED</Option>
        </Select>
      ),
    },
    {
      title: "Assigned To",
      dataIndex: "AssignedToUserId",
      width: 60,
    },
  ];

  useEffect(() => {
    fetchTasks();
  }, []);

  return (
    <>
      <h1 className="heading">All Tasks</h1>
      <Table
        rowKey="Id"
        dataSource={taskdata}
        columns={columns}
        loading={loading}
        pagination={false}
      />
    </>
  );
}

export default AllTaskPage;
