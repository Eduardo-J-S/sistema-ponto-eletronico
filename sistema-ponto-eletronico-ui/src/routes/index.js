import React, {useContext} from 'react';
import { PerfilContext } from '../contexts';

import Plataforma from './Plataforma';
import Entrada from './Entrada';

export default function Perfil() {
    const { signed, loading } = useContext(PerfilContext);

    if(loading){
        <div>Loading...</div>;
    }

    return (
        signed ?  <Plataforma /> : <Entrada />
    );
}