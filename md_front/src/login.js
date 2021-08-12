import './App.css';
import styled from 'styled-components';
import React from 'react';


function Login() {
    
    const onClick = () => {
      alert('로그인버튼 눌림');
    };

  return (
    <div>
      <Header>MetaData Managememt System</Header>
      <Container>
        <h1>Login</h1>
        <Input id="id" name="id" placeholder="아이디를 입력해주세요" />
        <Input
          id="password"
          name="password"
          type="password"
          placeholder="비밀번호를 입력해주세요"
        />
        <Button onClick={onClick}>로그인하기</Button>
      </Container>
    </div>
    
  );
}

const Header = styled.h1`
  justify-content: center;
  align-items: center;
  width: 50vw;
  flex-direction: column;
  margin: 0 auto;
`;

const Container = styled.div`
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

const Input = styled.input`
  position: relative;
  overflow: hidden;
  width: 100%;
  height: 40px;
  margin: 0 0 8px;
  padding: 5px 39px 5px 11px;
  border: solid 1px #dadada;
  background: #fff;
  box-sizing: border-box;
`;

const Button = styled.div`
  font-size: 18px;
  font-weight: 700;
  line-height: 49px;
  display: block;
  width: 100%;
  height: 49px;
  margin: 16px 0 7px;
  cursor: pointer;
  text-align: center;
  color: #fff;
  border: none;
  border-radius: 0;
  background-color: #03c7ff;
  ${({ disabled }) =>
    disabled &&
    `
    background-color: #efefef;
  `}
`;
export default Login;
