import React from 'react'
import { Layout, Button, Space } from "antd";
import "./AdminPage.scss";
import { useNavigate } from 'react-router-dom';


const { Header, Content } = Layout;

const AdminPage = () => {

  async function handleUsers(e){
    console.log("users button clicked")
    navigate("/users")
  }

  async function handleTasks(e){
    console.log("tasks button clicked")
    navigate("/tasks")
  }

  const navigate = useNavigate();

  return (
    <Layout className="admin-layout">

      <Header className="admin-navbar">
        <div className="admin-navbar__left">
          <h3>Navbar</h3>
        </div>

        <div className="admin-navbar__right">
          <Space>
            <Button onClick={handleUsers} type="primary">Users</Button>
            <Button onClick={handleTasks} type="primary">Tasks</Button>
            <Button type="primary">Add Task</Button>
          </Space>
        </div>
      </Header>

      <Content className="admin-content">
        <h1>Welcome to Admin Page</h1>
      </Content>

    </Layout>
  )
}

export default AdminPage
