import React from 'react'
import { Layout, Button, Space } from "antd";
import "./EmployeePage.scss";

const { Header, Content } = Layout;
const EmployeePage = () => {
  return (
    <Layout className="admin-layout">

      <Header className="admin-navbar">
        <div className="admin-navbar__left">
          <h3>Navbar</h3>
        </div>

        <div className="admin-navbar__right">
          <Space>
            <Button type="primary">My Tasks</Button>
            {/* <Button type="primary">Tasks</Button>
            <Button type="primary">Add Task</Button> */}
          </Space>
        </div>
      </Header>

      <Content className="admin-content">
        <h1>Welcome to Employee Page</h1>
      </Content>

    </Layout>
  )
}

export default EmployeePage
