
import styled from 'styled-components';
import React, { useEffect, useState } from 'react';
import Axios from 'axios';

function Login() {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')

  const handleEmail = (e) => {
    setEmail(e.target.value);
  }

  const handlePassword = (e) => {
    setPassword(e.target.value);
  }

  const onClickLogin = () => {
    Axios.post('/mms/user/join').then((response) => {
      if(response.data){
        console.log(response.data);
        // 현재 응답값은 널.
      } else {
        console.log('fail');
      }
    });
  }

  return (
    <div>
      <Container>
        <h1>Login</h1>
        <form action="/mms/user/join" method="post">
          <Input id="email" name="email"  placeholder="아이디(이메일)를 입력해주세요" />
          <Input
            id="password"
            name="password"
            type="password"
            placeholder="비밀번호를 입력해주세요"
          />
          <Button type="submit" onClick={onClickLogin}>로그인하기</Button>
        </form>
      </Container>
    </div>
  );
}


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