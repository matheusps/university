/*
Definition of the type Task
A Task has a title, a status(done or not) and a creation date
*/
export class Task{

  title: string;
  isDone: boolean;
  createdAt: Date;

  constructor(title: string){
    this.title = title;
    this.isDone = false;
    this.createdAt = new Date();
  }

}
