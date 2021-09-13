import React from 'react';
import styled from 'styled-components';
import Table from '../container/table';

function MainBox () {

    return (
        <div>
            <TableContainer>
                <Table/>
            </TableContainer>
        </div>
    );
}

const TableContainer = styled.div`
  margin-top: 100px;
  padding: 20px;
  width: 50vw;
  height: 50vh;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  overflow:hidden;
  margin-left: 25%;
  margin-top: 20%;
`;

export default MainBox;