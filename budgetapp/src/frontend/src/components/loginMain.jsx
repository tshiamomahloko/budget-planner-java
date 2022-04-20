import '../component-styles/loginMain.css'
import userico from '../userico.png'
import passico from '../passico.png'
import axios from 'axios'

function App(props) {
  let access = "";
  let createPost = () => {
    let username = document.getElementById(
      'loginUsernameContainerInputSpaceIdentifier'
    ).value
    let password = document.getElementById(
      'loginPasswordContainerInputSpaceIdentifier'
    ).value
    const data = { username: username, password: password }
    let url = 'http://localhost:8080/api/v1/users/auth/login'
    const options = {
      method: 'POST',
      headers: { 'content-type': 'application/json' },
      data: data,
      url
    }
    axios(options).then((response) => {
     // console.log(response)
     access =response.data.access_token;
     console.log(response);
     props.setAccessVar(access,1);
    }).catch((error)=>{
      console.log(error)
     alert("The username and password combination does not exist, Sign up?")
    })
  }
  return (
    <div className='loginMain'>
      <div className='loginContainer'>
        <button
          onClick={() => {
            createPost()
          }}
          className='loginMainLoginButton'
        >
          Log In
        </button>
        <div className='loginMainButtonSeperator'>- or -</div>
        <button
          onClick={() => props.FECresponse3()}
          className='loginMainSignUpButton'
        >
          Sign Up
        </button>

        <div className='loginEmailContainer'>
          <div className='loginEmailContainerIconSpace'>
            <img src={userico} className='loginIcons'></img>
          </div>
          <input
            id='loginUsernameContainerInputSpaceIdentifier'
            placeholder='Username'
            className='loginEmailContainerInputSpace'
          ></input>
        </div>
        <div className='loginPasswordContainer'>
          <div className='loginPasswordContainerIconSpace'>
            <img src={passico} className='loginIcons'></img>
          </div>
          <input
            id='loginPasswordContainerInputSpaceIdentifier'
            placeholder='Password'
            type='password'
            className='loginPasswordContainerInputSpace'
          ></input>
        </div>
      </div>

      <div className='loginTitle'>Welcome Back</div>
      <button onClick={() => props.FECresponse1()} className='loginBackButton'>
        {'< Back'}
      </button>
    </div>
  )
}

export default App
