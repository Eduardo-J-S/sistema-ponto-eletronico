import React, {createContext} from "react";

export const PerfilContext = createContext({});

const ContextProvider = ({ children }) => {

    return (
        <PerfilContext.Provider value={{
        }}>
            {children}
        </PerfilContext.Provider>
    )
}

export default ContextProvider;