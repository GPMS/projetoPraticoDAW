import { Link } from 'react-router-dom';
import { useState } from 'react'
import API from '../../services/api';
import '../admin/styles.css'

interface User {
  codigo: number;
  nome: string;
  email: string;
}

export default function Admin() {

  const [data, setData] = useState([]);
  const [loaded, setLoaded] = useState(false);

  const getData = () => {
    API.get('')
      .then((response) => {
        const myData = response.data._embedded;
        if (!response.data._embedded) {
          setData([]);
        } else {
          setData(myData.dTOList);
        }
      })
      .catch(error => {
        alert("Não foi possível pegar a lista de usuários\n" + error);
      })
  }
  if (!loaded) {
    getData();
    setLoaded(true);
  }

  if (data.length === 0) {
    return (
      <>
      <h1>Não há usuários cadastrados no servidor!</h1>
      <p>Tente cadastrar um usuário <Link to='/user'>aqui</Link>.</p>
      </>
    )
  }
  return (
    <>
      <h1>Usuários</h1>
      <table>
      <tbody>
        <tr>
          <th>Código</th>
          <th>Nome</th>
          <th>Email</th>
        </tr>
        {data.map((user: User) =>
          <tr key = {user.codigo}>
            <td>{user.codigo}</td>
            <td>{user.nome}</td>
            <td>{user.email}</td>
          </tr>
        )}
      </tbody>
      </table>
    </>
  )
}
