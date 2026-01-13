import React, { useEffect, useState } from 'react'
import { allUsers } from '../../api/allusers'
import { Table } from 'antd';
import "./UserPage.scss"

function UserPage() {

  const [userdata, setUserdata] = useState([])
  const [columns, setColumns] = useState([])

  async function fetchusers() {
    const response = await allUsers()
    const data = response.data

    const formattedData = data.map((user) => ({
      key: user.id,        
      Id: user.id,
      Name: user.name,
      Email: user.email,
      Role: user.role,
    }))

    setUserdata(formattedData)

    setColumns([
      {
        title: 'ID',
        dataIndex: 'Id',
        width: 60,
      },
      {
        title: 'Name',
        dataIndex: 'Name',
        width: 120,
      },
      {
        title: 'Email',
        dataIndex: 'Email',
        width: 200,
      },
      {
        title: 'Role',
        dataIndex: 'Role',
        width: 100,
      },
    ])
  }

  useEffect(() => {
    fetchusers()
  }, [])

  return (
    <>
      <h1 className='heading'>All users</h1>
      <Table dataSource={userdata} columns={columns} pagination = {false} />
    </>
  )
}

export default UserPage
