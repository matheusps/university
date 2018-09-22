/*
Simple mock of default tasks that can be easily replaced.
*/
import { Task } from './task';

export const TASKS: Task[] = [

  { title:"Capinar um lote", isDone: false, createdAt: new Date()},
  { title:"Pular de paraquedas de um prédio", isDone: true, createdAt: new Date() },
  { title:"Escalar o monte everest", isDone: false, createdAt: new Date() },
  { title:"Comer feijão com oreo", isDone: true, createdAt: new Date() },
  { title:"Chutar um dragão de komodo", isDone: true, createdAt: new Date() }
  
];
