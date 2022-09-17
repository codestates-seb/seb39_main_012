import {NextApiRequest, NextApiResponse} from 'next'
import {db} from './db'

export default function handler(req: NextApiRequest, res: NextApiResponse) {
  const user = req.body

  if (user) {
    db.push(user)
    return res.status(200).json({message: 'success'})
  }

  return res.status(400).json({message: 'fail'})
}
