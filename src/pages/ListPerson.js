import PersonService from "../services/PersonService";
import axios from "axios";
import { useHistory } from "react-router-dom";
import React, { useEffect, useState } from 'react';
import MaterialTable, { MTableFilterRow } from 'material-table';
import { Alert, AlertTitle } from '@material-ui/lab';
import { Form, Input, Button } from "antd";

const ListPerson = (props) => {

    const [person, setPerson] = useState([]);

    const [iserror, setIserror] = useState(false);
    const [errorMessages, setErrorMessages] = useState([]);

    let columns = [
        { title: 'TC Kimlik Numarası', field: 'tcNumber', editable: 'never' },
        { title: 'Ad', field: 'name' },
        { title: 'Soyad', field: 'surname' },
        { title: 'Adres', field: 'address' },
        { title: 'Gelir', field: 'income', type: 'numeric' }
    ]

    useEffect(() => {
        axios.get(`http://localhost:8080/person/`)
            .then(res => {
                const persons = res.data;
                setPerson(persons);
            })
    }, [])

    //function for updating the existing row details
    const handleRowUpdate = (newData, oldData, resolve) => {
        //validating the data inputs
        let errorList = []
        if (newData.title === "") {
            errorList.push("Try Again, You didn't enter the title field")
        }
        if (newData.price === "") {
            errorList.push("Try Again, You didn't enter the price field")
        }
        if (newData.seller === "") {
            errorList.push("Seller field can't be blank")
        }

        if (errorList.length < 1) {
            axios.put(`http://localhost:8080/person/`, newData)
                .then(response => {
                    const updatePerson = [...person];
                    const index = oldData.tableData.id;
                    updatePerson[index] = newData;
                    setPerson([...updatePerson]);
                    resolve()
                    setIserror(false)
                    setErrorMessages([])
                })
                .catch(error => {
                    setErrorMessages(["Update failed! Server error"])
                    setIserror(true)
                    resolve()

                })
        } else {
            setErrorMessages(errorList)
            setIserror(true)
            resolve()
        }
    }


    //function for deleting a row
    const handleRowDelete = (oldData, resolve) => {
        axios.delete(`http://localhost:8080/person/${oldData.id}`)
            .then(response => {
                const dataDelete = [...person];
                const index = oldData.tableData.id;
                dataDelete.splice(index, 1);
                setPerson([...dataDelete]);
                resolve()
            })
            .catch(error => {
                setErrorMessages(["Delete failed! Server error"])
                setIserror(true)
                resolve()
            })
    }


    //function for adding a new row to the table
    const handleRowAdd = (newData, resolve) => {
        //validating the data inputs
        let errorList = []
        if (newData.title === "") {
            errorList.push("Try Again, You didn't enter the title field")
        }
        if (newData.price === "") {
            errorList.push("Try Again, You didn't enter the price field")
        }

        if (errorList.length < 1) {
            axios.post(`http://localhost:8080/api/person/`, newData)
                .then(response => {
                    let newUserdata = [...person];
                    newUserdata.push(newData);
                    setPerson(newUserdata);
                    resolve()
                    setErrorMessages([])
                    setIserror(false)
                })
                .catch(error => {
                    setErrorMessages(["Cannot add data. Server error!"])
                    setIserror(true)
                    resolve()
                })
        } else {
            setErrorMessages(errorList)
            setIserror(true)
            resolve()
        }
    }


    return (
        <div className="app">
            <MaterialTable
                title="Kullanıcılar"
                columns={columns}
                data={person}
                options={{
                    headerStyle: { borderBottomColor: 'red', borderBottomWidth: '3px', fontFamily: 'verdana' },
                    actionsColumnIndex: -1,
                    search: false
                }}
                editable={{
                    onRowUpdate: (newData, oldData) =>
                        new Promise((resolve) => {
                            handleRowUpdate(newData, oldData, resolve);
                        }),
                    onRowAdd: (newData) =>
                        new Promise((resolve) => {
                            handleRowAdd(newData, resolve)
                        }),
                    onRowDelete: (oldData) =>
                        new Promise((resolve) => {
                            handleRowDelete(oldData, resolve)
                        }),
                }}
            />
            <div>
                {iserror &&
                    <Alert severity="error">
                        <AlertTitle>ERROR</AlertTitle>
                        {errorMessages.map((msg, i) => {
                            return <div key={i}>{msg}</div>
                        })}
                    </Alert>
                }
            </div>
        </div>
    );
}

export default ListPerson;