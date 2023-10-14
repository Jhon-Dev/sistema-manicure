import React, { useEffect, useState } from 'react';
import { Box, Typography, useTheme } from "@mui/material";
import { DataGrid } from "@mui/x-data-grid";
import { tokens } from "../../theme";
import IconButton from "@mui/material/IconButton";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";
import Header from "../../components/Header";
import axios from 'axios';

const Cliente = () => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  const [clientes, setClientes] = useState([]); 

  useEffect(() => {
    const fetchClientes = async () => {
      try {
      const token = localStorage.getItem('authToken'); 
      if (!token) {
          console.error('Token não encontrado no armazenamento.');
          window.location.href = '/login';
          return;
      }
        const config = {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        };
        const response = await axios.get('http://localhost:8080/api/user/list', config);
        const data = response.data.content;
        setClientes(data);
      } catch (error) {
          window.location.href = '/login';
          console.error('Erro ao buscar clientes:', error);
      }
    };
  
    fetchClientes();
  }, []);

    const handleDeleteClick = async (id) => {
        try {
            const token = localStorage.getItem('authToken');
            if (!token) {
                console.error('Token não encontrado no armazenamento.');
                window.location.href = '/login';
                return;
            }

            const config = {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            };

            const response = await axios.delete(`http://localhost:8080/api/user/delete/${id}`, config);

            if (response.status === 200) {
                console.log('Usuário excluído com sucesso.');
                // Coloque aqui alguma lógica para atualizar a lista de clientes após a exclusão, se necessário.
            } else {
                console.error('Falha ao excluir o usuário:', response.data);
            }
        } catch (error) {
            console.error('Erro ao excluir o usuário:', error);
        }
    };

  const columns = [
    { field: "id", headerName: "ID" },
    {
      field: "firstname",
      headerName: "Nome",
      flex: 1,
      cellClassName: "name-column--cell",
    },

    {
      field: "phone",
      headerName: "Celular",
      flex: 1,
    },
    {
      field: "email",
      headerName: "Email",
      flex: 1,
    },
    {
      field: "packageMonthly",
      headerName: "Pacote Mensal",
      flex: 1,
    },
      {
          field: "edit",
          headerName: "Editar",
          flex: 1,
          renderCell: (row) => {
              return (
                  <Box>
                      <IconButton>
                          <EditIcon />
                      </IconButton>
                      <IconButton onClick={() => handleDeleteClick(row.id)}>
                          <DeleteIcon />
                      </IconButton>
                  </Box>
              );
          },
      },
  ];

  return (
    <Box m="20px">
      <Header title="Clientes" subtitle="" />
      <Box
        m="40px 0 0 0"
        height="75vh"
        sx={{
          "& .MuiDataGrid-root": {
            border: "none",
          },
          "& .MuiDataGrid-cell": {
            borderBottom: "none",
          },
          "& .name-column--cell": {
            color: colors.greenAccent[300],
          },
          "& .MuiDataGrid-columnHeaders": {
            backgroundColor: colors.blueAccent[700],
            borderBottom: "none",
          },
          "& .MuiDataGrid-virtualScroller": {
            backgroundColor: colors.primary[400],
          },
          "& .MuiDataGrid-footerContainer": {
            borderTop: "none",
            backgroundColor: colors.blueAccent[700],
          },
          "& .MuiCheckbox-root": {
            color: `${colors.greenAccent[200]} !important`,
          },
        }}
      >
        <DataGrid checkboxSelection rows={clientes} columns={columns} />
      </Box>
    </Box>
  );
};

export default Cliente;
