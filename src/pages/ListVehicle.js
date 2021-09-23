import { Button, Form } from "antd";
import axios from "axios";
import MaterialTable from 'material-table';
import React, { useEffect, useState } from 'react';
import { useHistory } from "react-router-dom";
import { Alert, AlertTitle } from '@material-ui/lab';

const ListVehicle = (props) => {

    const history = useHistory();

    const [vehicle, setVehicle] = useState([]);
    const [selectedRow, setSelectedRow] = useState(false);
    const [select, setSelect] = useState([]);

    const [iserror, setIserror] = useState(false);
    const [errorMessages, setErrorMessages] = useState([]);

    const [isSuccesfull, setIsSuccesfull] = useState(false);
    const [successMessages, setSuccessMessages] = useState([]);

    let columns = [
        { title: 'Plaka', field: 'plateNumber' },
        { title: 'Şasi No', field: 'chassisNumber' },
        { title: 'Renk', field: 'color' },
        { title: 'Araç Yaşı', field: 'age' },
        { title: 'Araç Kilometresi', field: 'kilometer' },
    ]

    useEffect(() => {
        axios.get(`http://localhost:8080/person/vehicle/${props.match.params.tc}`)
            .then(res => {
                const vehicles = res.data;
                setVehicle(vehicles);
            })
    }, [])

    const onFinish = async (values) => {
        if (select !== null) {
            history.push(`/list-details/${select}`)
        }
        else {
            alert("Araçlardan birini seçmelisin.")
        }
    };

    const onFinishFailed = (errorInfo) => {
        console.log("Failed:", errorInfo);
    };

    const handleRowAdd = (newData, resolve) => {
        //validating the data inputs
        let errorList = []
        console.log(newData)
        if (newData.chassisNumber == null) {
            errorList.push("Şasi No alanı boş bırakılamaz")
        }
        if (newData.plateNumber == null) {
            errorList.push("Plaka alanı boş bırakılamaz")
        }
        if (newData.color == null) {
            errorList.push("Renk alanı boş bırakılamaz")
        }
        if (newData.age == null) {
            errorList.push("Araç Yaşı alanı boş bırakılamaz")
        }
        if (newData.kilometer == null) {
            errorList.push("Araç Kilometresi alanı boş bırakılamaz")
        }

        if (errorList.length < 1) {
            axios.put(`http://localhost:8080/person/vehicle/${props.match.params.tc}`, newData)
                .then(response => {
                    let newVehicleData = [...vehicle];
                    newVehicleData.push(newData);
                    setVehicle(newVehicleData);
                    resolve()
                    setErrorMessages([])
                    setIserror(false)
                    setIsSuccesfull(true)
                    setSuccessMessages([response.data.message])
                })
                .catch(error => {
                    setErrorMessages(["Cannot add data. Server error!"])
                    setIserror(true)
                    setIsSuccesfull(false)
                    setSuccessMessages([])
                    resolve()
                })
        } else {
            setErrorMessages(errorList)
            setIserror(true)
            setIsSuccesfull(false)
            setSuccessMessages([])
            resolve()
        }
    }

    return (
        <div className="app">
            <MaterialTable
                title="Araç Bilgileri"
                columns={columns}
                data={vehicle}
                options={{
                    headerStyle: { borderBottomColor: 'red', borderBottomWidth: '3px', fontFamily: 'verdana' },
                    actionsColumnIndex: -1,
                    search: false,
                    rowStyle: rowData => ({
                        backgroundColor: (selectedRow === rowData.tableData.id) ? '#7cabe1' : '#FFF'
                    })
                }}
                onRowClick={((evt, selectedRow) => {
                    setSelectedRow(selectedRow.tableData.id)
                    setSelect(selectedRow.chassisNumber)
                    console.log(selectedRow.chassisNumber)
                })}
                editable={{
                    onRowAdd: (newData) =>
                        new Promise((resolve) => {
                            handleRowAdd(newData, resolve)
                        }),
                }}
            />
            <div>
                {iserror &&
                    <Alert severity="error" variant="filled">
                        <AlertTitle>HATA</AlertTitle>
                        {errorMessages.map((msg, i) => {
                            return <div><strong key={i}>{msg}</strong><br /></div>
                        })}
                    </Alert>
                }
                {
                    isSuccesfull &&
                    <Alert severity="success" variant="filled">
                        <AlertTitle>BAŞARILI</AlertTitle>
                        {successMessages.map((msg, i) => {
                            return <strong key={i}>{msg}</strong>
                        })}
                    </Alert>
                }
            </div>
            <Form
                name="basic"
                labelCol={{
                    span: 8
                }}
                wrapperCol={{
                    span: 16
                }}
                initialValues={{
                    remember: true
                }}
                onFinish={onFinish}
                onFinishFailed={onFinishFailed}
                style={{ margin: "0 auto", width: 400 }}
            >
                <Form.Item
                    wrapperCol={{
                        offset: 8,
                        span: 16
                    }}
                >
                    <Button type="primary" htmlType="submit">
                        Teklifleri Al
                    </Button>
                </Form.Item>


            </Form>
        </div>
    );
}

export default ListVehicle;