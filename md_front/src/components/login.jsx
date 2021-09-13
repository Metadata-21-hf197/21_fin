
import styled from 'styled-components';
import React, { useState } from 'react';


function Login( {history} ) {
  const [memberName, setMemberName] = useState('')
  const [password, setPassword] = useState('')


  // user/test경로로 서버에서 응답받아오는것 확인함. 변경..
  // 메소드만 post로 변경
  //밑 폼 액션쪽에서도 수정해야함.
  const onClickLogin = () => {
    Axios.post('/user/login').then((response) => {
      if(response.data){
        console.log(response.data);
        // 현재 응답값은 널.
        history.push('/main');
      } else {
        console.log('fail');
      }
    });
  }
  /*const onClickLogin = () => {
    history.push('/main');
  };*/

  const onClickJoin = () => {
    history.push('/join');
  };

  return (
    <div>
      <Container>
        <h1>Login</h1>
        <form action="/user/login" method="post"> 
          <Input value={memberName} onChange={(e) => setMemberName(e.target.value)} placeholder="아이디를 입력해주세요" />
          <Input
            value={password} onChange={(e) => setPassword(e.target.password)}
            placeholder="비밀번호를 입력해주세요"
          />
        </form>
        <Button type="submit" onClick={onClickLogin}>로그인</Button>
        <Button type="submit" onClick={onClickJoin}>회원가입</Button>
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