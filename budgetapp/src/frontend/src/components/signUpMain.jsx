import '../component-styles/signUpMain.css'
import userico from '../userico.png'
import emailico from '../emailico.png'
import passico from '../passico.png'
import axios from 'axios'

function App(props) {
  let apiData = {}
  let username = ''
  let email = ''
  let password = ''
  let access = ''

  let createPost = () => {
    username = document.getElementById(
      'signUpNameContainerInputSpaceIdentifier'
    ).value
    email = document.getElementById(
      'signUpEmailContainerInputSpaceIdentifier'
    ).value
    password = document.getElementById(
      'signUpPasswordContainerInputSpaceIdentifier'
    ).value

    const data = { username: username, password: password, email: email }
    let url = 'http://localhost:8080/api/v1/users/auth/signup'
    const options = {
      method: 'POST',
      headers: { 'content-type': 'application/json' },
      data: data,
      url
    }
    axios(options)
      .then((response) => {
        apiData = response
      })
      .catch((error) => {
        alert('Error: username or email already exists')
      })

    setTimeout(() => {
      if (apiData.status === 200) {
        loginUser(username, password)
        // props.setAccessVar(access, username)
        props.FECresponse3()
      }
    }, 3000)
  }

  let loginUser = (username, password) => {
    const data = { username: username, password: password }
    let url = 'http://localhost:8080/api/v1/users/auth/login'
    const options = {
      method: 'POST',
      headers: { 'content-type': 'application/json' },
      data: data,
      url
    }
    axios(options).then((response) => {
      console.log('Login data: ', response)
      access = response.data.access_token
    })
  }
  return (
    <div className='signUpMain'>
      <div className='signUpContainer'>
        <button
          onClick={() => props.FECresponse2()}
          className='signUpMainLoginButton'
        >
          Log In
        </button>
        <div className='signUpMainButtonSeperator'>- or -</div>
        <button
          onClick={() => {
            createPost()
          }}
          className='signUpMainSignUpButton'
        >
          Sign Up
        </button>

        <div className='signUpNameContainer'>
          <div className='signUpNameContainerIconSpace'>
            <img src={userico} className='signUpIcons'></img>
          </div>
          <input
            id='signUpNameContainerInputSpaceIdentifier'
            placeholder='Username'
            className='signUpNameContainerInputSpace'
          ></input>
        </div>
        <div className='signUpEmailContainer'>
          <div className='signUpEmailContainerIconSpace'>
            <img src={emailico} className='signUpIcons'></img>
          </div>
          <input
            id='signUpEmailContainerInputSpaceIdentifier'
            placeholder='Email'
            className='signUpEmailContainerInputSpace'
          ></input>
        </div>
        <div className='signUpPasswordContainer'>
          <div className='signUpPasswordContainerIconSpace'>
            <img src={passico} className='signUpIcons'></img>
          </div>
          <input
            id='signUpPasswordContainerInputSpaceIdentifier'
            placeholder='Password'
            type='password'
            className='signUpPasswordContainerInputSpace'
          ></input>
        </div>
      </div>

      <div className='signUpTitle'>Create Account</div>
      <button onClick={() => props.FECresponse1()} className='signUpBackButton'>
        {'< Back'}
      </button>
    </div>
  )
}

export default App
