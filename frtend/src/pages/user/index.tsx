import { useState } from 'react'
import API from '../../services/api';
import '../user/styles.css'
import Lock from '../../assets/images/padlock.png'

import '../../services/api'

import { MdForum, MdMail } from "react-icons/md"

export default function User() {
  const [name, setName] = useState("")
  const [email, setEmail] = useState("")

  async function submit(e: any) {
    e.preventDefault();
    const data = {
      'nome': name,
      'email': email,
    };

    (async () => {
      API.post('', data)
      .then(response => {
        alert('Usuário cadastrado com sucesso!');
      })
      .catch(error => {
          if (error.response) {
            let errorMessage = error.response.data.description;
            if (error.response.data.fields) {
              for (var field of error.response.data.fields) {
                errorMessage += '\n' + field.name + ':' + field.message;
              }
            }
            alert(errorMessage);
          } else {
            alert("Não foi possível cadastrar usuário!\n" + error);
          }
      })
    })();
  }

  return (
    <>
      <div className="login">
        <div className="login-logo">
          <img
            src={Lock}
            alt="MdLockLogin App"
          />
        </div>

        <form onSubmit={submit}>
          <div className="login-right">
            <h1>C A D A S T R O</h1>

            <div className="login-input-box">
              <MdForum />
              <input
                type="text"
                placeholder="Digite seu nome"
                value={name}
                onChange={e => setName(e.target.value)}
              />
            </div>

            <div className="login-input-box">
              <MdMail />
              <input
                type="email"
                placeholder="Digite seu email"
                value={email}
                onChange={e => setEmail(e.target.value)}
              />
            </div>

            <button type="submit">
              C A D A S T R A R
            </button>
          </div>
        </form>
      </div>
    </>
  )
}
