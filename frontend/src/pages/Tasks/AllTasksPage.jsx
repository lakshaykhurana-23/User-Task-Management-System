import React, { useEffect, useState } from "react";
import { allTasks } from "../../api/allTasks";
import { updateTask } from "../../api/updateTask";
import { Table, Select, message } from "antd";
import "./AllTaskpage.scss";

const { Option } = Select;

function AllTaskPage() {
  const [taskdata, setTaskdata] = useState([]);
  const [loading, setLoading] = useState(false);

  // -------------------------------
  // FETCH TASKS
  // -------------------------------
  const fetchTasks = async () => {
    setLoading(true);
    try {
      const response = await allTasks();
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

  // -------------------------------
  // HANDLE STATUS / PRIORITY CHANGE
  // -------------------------------
  const handleTaskChange = async (taskId, changes) => {
  const oldTask = taskdata.find(task => task.Id === taskId);
  if (!oldTask) return;

  // FULL PAYLOAD AS BACKEND EXPECTS
  const payload = {
    // title: oldTask.Title,
    // description: oldTask.Description,
    priority: changes.priority ,
    status: changes.status ,
    // assignedToUserId: oldTask.AssignedToUserId,
  };
  

  // optimistic UI update
  setTaskdata(prev =>
    prev.map(task =>
      task.Id === taskId ? { ...task, ...changes } : task
    )
  );

  try {
    await updateTask(taskId, payload);
  } catch (err) {
    console.error(err);

    // rollback on failure
    setTaskdata(prev =>
      prev.map(task =>
        task.Id === taskId ? oldTask : task
      )
    );
  }
};



  // -------------------------------
  // TABLE COLUMNS
  // -------------------------------
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

  // -------------------------------
  // EFFECT
  // -------------------------------
  useEffect(() => {
    fetchTasks();
  }, []);

  // -------------------------------
  // RENDER
  // -------------------------------
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
