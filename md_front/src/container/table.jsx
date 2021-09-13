import React from 'react';
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';


function Table () {

    const products = [{
        id: 1,
        name: "Product1",
        price: 120
    }, {
        id: 2,
        name: "Product2",
        price: 80
    }];


    return (
        <BootstrapTable data={ products } height='120' scrollTop={ 'Bottom' }>
            <TableHeaderColumn dataField='id' isKey>ID</TableHeaderColumn>
            <TableHeaderColumn dataField='name'>Short Name</TableHeaderColumn>
            <TableHeaderColumn dataField='name'>Eng Name</TableHeaderColumn>
            <TableHeaderColumn dataField='name'>Kor Name</TableHeaderColumn>
            <TableHeaderColumn dataField='name'>Meaning</TableHeaderColumn>
            <TableHeaderColumn dataField='name'>Type</TableHeaderColumn>
            <TableHeaderColumn dataField='name'>Ban Word</TableHeaderColumn>
        </BootstrapTable>
    );
}

export default Table;