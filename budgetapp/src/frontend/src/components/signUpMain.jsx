import "../component-styles/signUpMain.css";
import userico from "../userico.png";
import emailico from "../emailico.png";
import passico from "../passico.png";
import axios from 'axios'

function App(props) {
  let createPost = ()=>{
    const data = { username: 'tshiamo', password: 'mahlokohum', email: 'tshiamo@gmail.com'}
    let url = 'http://localhost:8080/api/v1/users/auth/signup'
    const options = {
      method: 'POST',
      headers: { 'content-type': 'application/json' },
      data: data,
      url
    }
    axios(options).then((response) => {
      console.log(response)
    })
  }
  return (
    <div className="signUpMain">
      <div className="signUpContainer">
        <button
          onClick={() => props.FECresponse2()}
          className="signUpMainLoginButton"
        >
          Log In
        </button>
        <div className="signUpMainButtonSeperator">- or -</div>
        <button
          onClick={() =>{
            createPost()
            props.FECresponse3() 
            
          } }
          className="signUpMainSignUpButton"
        >
          Sign Up
        </button>

        <div className="signUpNameContainer">
          <div className="signUpNameContainerIconSpace">
            <img src={userico} className="signUpIcons"></img>
          </div>
          <input
            placeholder="Name"
            className="signUpNameContainerInputSpace"
          ></input>
        </div>
        <div className="signUpEmailContainer">
          <div className="signUpEmailContainerIconSpace">
            <img src={emailico} className="signUpIcons"></img>
          </div>
          <input
            placeholder="Email"
            className="signUpEmailContainerInputSpace"
          ></input>
        </div>
        <div className="signUpPasswordContainer">
          <div className="signUpPasswordContainerIconSpace">
            <img src={passico} className="signUpIcons"></img>
          </div>
          <input
            placeholder="Password"
            type="password"
            className="signUpPasswordContainerInputSpace"
          ></input>
        </div>
      </div>

      <div className="signUpTitle">Create Account</div>
      <button onClick={() => props.FECresponse1()} className="signUpBackButton">
        {"< Back"}
      </button>
    </div>
  );
}

export default App;
