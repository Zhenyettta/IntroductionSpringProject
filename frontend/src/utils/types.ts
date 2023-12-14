export type Indexable = {
    [key: string]: never
}

export type Credentials = {
    username: string
    password: string
}

export type Category = {
    id: number
    name: string
    description: string
}

export type Product = {
    id: number
    name: string
    description: string
    producer: string
    inStockAmount: number
    price: number
    category: Category
}

export type User= {
    username: string
    role: string
    password: string
    is_password_default: boolean
}
